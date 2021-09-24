from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class ClickedHobbyClass(db.Model):
  __tablename__ = "clicked_hobby_class"

  id = db.Column(db.BigInteger, nullable=False, autoincrement=True, primary_key=True)
  count = db.Column(db.Integer, nullable=False)
  date = db.Column(db.DateTime, nullable=False)
  user_id = db.Column(db.BigInteger)
  hobby_class_id = db.Column(db.BigInteger)

  def __repr__(self):
    return f"[ClickedHobbyClass] ({self.id}, {self.count}, {self.user_id}, {self.hobby_class_id})"

  def as_dict(self):
    return {x.name: getattr(self, x.name) for x in self.__table__.columns}