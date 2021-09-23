from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class InterestCategory(db.Model):
  __tablename__ = "interest_category"

  id = db.Column(db.BigInteger, nullable=False, autoincrement=True, primary_key=True)
  user_id = db.Column(db.BigInteger)
  small_category_id = db.Column(db.Integer)

  def __repr__(self):
    return f"[InterestCategory] ({self.id}, {self.user_id}, {self.small_category_id})"

  def as_dict(self):
    return {x.name: getattr(self, x.name) for x in self.__table__.columns}